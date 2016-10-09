# Classification model for survival using titanic data
# Random forest model is used
# Reference: Megan Risdal and Ben Hamner

library(mice)
library(randomForest)
library(ggplot2)
library(ggthemes)

# Load csv files -----------------------------------------------------------------------

train <- read.csv("../input/train.csv", stringsAsFactors=FALSE)
test  <- read.csv("../input/test.csv",  stringsAsFactors=FALSE)


# Feature extraction from each variable ------------------------------------------------

d0 <- merge(train, test, all=T)

# Make empty characters NA
for(i in 1:ncol(d0)){
  if(is.character(d0[,i])){
    d0[d0[,i] == '', i] <- NA
  }
}

summary(d0)
apply(d0, 2, function(x){sum(is.na(x))})

# PassengerId have no information
d0$PassengerId <- NULL

# Pclass is factor
unique(d0$Pclass)
d0$Pclass <- factor(d0$Pclass)

# Extract 'title' information from Name variable (thx Megan)
extract_title <- function(name){
  title <- gsub('(.*, )|(\\..*)', '', name)
  
  # language translation
  title[title == 'Mlle'] <- 'Miss' 
  title[title == 'Ms']   <- 'Miss'
  title[title == 'Mme']  <- 'Mrs' 
  
  # summarize rare titles
  tb <- table(title)
  title[title %in% names(tb)[tb < 10]] <- 'Rare'
  
  cat(noquote(paste('Titles summary:')))
  print(table(d0$Sex, title))
  
  title
}
d0$Title <- factor(extract_title(d0$Name))
d0$Name <- NULL

# Sex is factor
d0$Sex <- factor(d0$Sex)

# Extract 'FamilySize' from SibSp and Parch variables (thx Megan)
# Note - this variable may be harmful for linear models.
d0$FamilySize <- d0$SibSp + d0$Parch + 1

# Cabin has too many NA
d0$Cabin <- NULL

d0$Ticket <- NULL

# Embarked is factor
d0$Embarked <- factor(d0$Embarked)

# Survived is factor
d0$Survived <- factor(d0$Survived)

# Training and testing data ------------------------------------------------------------

# TODO caret

tr <- head(d0, nrow(train))
tr <- tr[complete.cases(tr),] # no imputation in training

cv <- tail(d0, nrow(test))
cv$Survived <- NULL

apply(cv, 2, function(x){sum(is.na(x))})

# imputation usint mice package
mice_fit <- mice(cv, method = 'rf')
cv <- complete(mice_fit)

# Plain statistics ---------------------------------------------------------------------

# TODO

# modeling -----------------------------------------------------------------------------

fit <- randomForest(Survived ~ ., data = tr)
plot(fit)
legend('topright', colnames(fit$err.rate), col=1:3, fill=1:3)

imp <- importance(fit) # type = 2, Gini
imp <- as.data.frame(imp)

p <- ggplot(imp, aes(x = reorder(rownames(imp), MeanDecreaseGini) , y=MeanDecreaseGini)) +
  geom_bar(stat="identity") +
  xlab("Features") +
  ggtitle("Random Forest Feature Importance\n") +
  coord_flip() +
  theme_few()

ggsave("feature_importance.png", p)


# output csv ---------------------------------------------------------------------------

pred <- data.frame(
  PassengerId = test$PassengerId,
  Survived = predict(fit, cv)
)

write.csv(pred, file = "pred.csv", row.names=FALSE)
