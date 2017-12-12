function xdot = prob914(t,x)
xdot = zeros(2,1);
xdot(1) = 1*x(1) - 4*x(2);
xdot(2) = 4*x(1) - 7*x(2);