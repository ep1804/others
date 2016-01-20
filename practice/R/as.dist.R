m  = matrix(c(0,1,2,3, 1,0,3,4, 2,3,0,5, 3,4,5,0), nrow=4)
md = as.dist(m, diag=F)
mdv = as.vector(md)

fun <- function(r, c){
  stopifnot(r != c)
  if(r > c) (r-2)*(r-1)/2 + c
  else (c-2)*(c-1)/2 + r
}

mdv[fun(1, 2)] # 1
mdv[fun(2, 3)] # 3
mdv[fun(3, 4)] # 5
mdv[fun(2, 1)] # 1
mdv[fun(3, 2)] # 3
mdv[fun(1, 1)] # stop
