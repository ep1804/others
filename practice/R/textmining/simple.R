library(tm)
library(topicmodels)

docs <- c("This is a text.", "This another one.")
docs <- VCorpus(VectorSource(docs))
docs <- tm_map(docs, content_transformer(tolower))

docs <- tm_map(docs, removePunctuation)
docs <- tm_map(docs, removeNumbers)
docs <- tm_map(docs, removeWords, stopwords('english'))
docs <- tm_map(docs, stripWhitespace)

writeLines(as.character(docs[[1]]))
writeLines(as.character(docs[[2]]))

dtm <- DocumentTermMatrix(docs)

lda <- LDA(dtm, 3)

print(topics(lda))
print(as.matrix(terms(lda, 2)))
