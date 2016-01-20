library(sp)
data(meuse)
data(meuse.grid)
coordinates(meuse) <- c("x", "y")
coordinates(meuse.grid) <- c("x", "y")
gridded(meuse.grid) <- TRUE
col=colorRampPalette(c("red","blue"))(30)
meuse.grid@data$var.1=sample(11:40,length(meuse.grid@data[,1]),replace=TRUE)
meuse.grid@data$var.2=sample(5:30,length(meuse.grid@data[,1]),replace=TRUE)

p1 = spplot(meuse.grid, "var.1", col.regions=col)
p2 = spplot(meuse.grid, "var.2", col.regions=col)

print(p1, position = c(0, .5, .5, 1),more=T)
print(p2, position = c(.5, .5, 1, 1),more=F)
