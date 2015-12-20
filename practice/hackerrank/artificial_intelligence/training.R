stdIn <- function(){
  line <- readLines(file('stdin'), n=1)[[1]]
  as.vector(sapply(strsplit(line, "\\s+"), as.numeric))
}

stdOut <- function(num){
  writeLines(as.character(round(num, 2)))
}

# read number of features, number of training samples
a <- stdIn()
fs <- a[1]
n <- a[2]

# read training data
tr <- NULL
for(i in 1:n)
  tr <- c(tr, stdIn())
tr <- as.data.frame(matrix(tr, nrow=n, byrow=T))

# read test (observation) cases
n <- stdIn()
ob <- NULL
for(i in 1:n)
  ob <- c(ob, stdIn())
ob <- as.data.frame(matrix(ob, nrow=n, byrow=T))

# align names for linear regression functions: lm and predict

xname <- paste(rep('x', fs), 1:fs, sep='')
colnames(tr) <- c(xname, 'y')
colnames(ob) <- xname

# build formula, fit model and estimate
formula <- paste('y ~', paste(xname, collapse=' + ') )
fit <- lm(formula, tr)
est <- predict(fit, ob)

# print estimated values
for(i in 1:length(est))
  stdOut(est[i])
