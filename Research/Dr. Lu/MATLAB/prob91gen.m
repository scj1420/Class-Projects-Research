function xdot = prob91gen(t,x,flags,c)
xdot = zeros(2,1);
xdot(1) = c(1)*x(1) + c(2)*x(2);
xdot(2) = c(3)*x(1) + c(4)*x(2);