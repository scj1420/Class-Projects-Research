function xdot = prob912(t,x)
xdot = zeros(2,1);
xdot(1) = 5*x(1) - x(2);
xdot(2) = 3*x(1) + x(2);