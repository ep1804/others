library(ggplot2)

ps <- read.csv("point100.csv")
ps <- data.frame(ps, color=1:nrow(ps))

hull <- read.csv("hull100.csv")
hull <- hull[c(1:nrow(hull),1),]
hull <- data.frame(hull, alpha=1:nrow(hull))

p <- ggplot(data=ps, aes(x=x, y=y)) + 
  geom_point(aes(color=color)) +
  geom_point(data=hull[1,], color="red") +
  geom_path(data=hull, aes(alpha=alpha))

print(p)
