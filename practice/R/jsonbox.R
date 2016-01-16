library(jsonlite)

y <- '[{"a":1, "b":"select", "c":["A", "B", "C"]}, 
       {"a":2, "b":"text"}, 
       {"a":3, "b":"select", "c":["D", "E", "F", "G"]}]'

# first solution
z <- fromJSON(y, simplifyDataFrame = F)
yy <- toJSON(z, auto_unbox=T)
print(yy)


y2 <- '[{"a":1, "b":"select", "c":["A", "B", "C"]}, 
        {"a":2, "b":"text"}, 
{"a":3, "b":"select", "c":["D"]}]'

z2 <- fromJSON(y2, simplifyDataFrame = F)
toJSON(z2, auto_unbox=T)

z2 <- fromJSON(y2, simplifyDataFrame = F)
for(i in 1:length(z2)){
  z2[[i]][[1]] <- unbox(z2[[i]][[1]])
  z2[[i]][[2]] <- unbox(z2[[i]][[2]])
}
toJSON(z2)
