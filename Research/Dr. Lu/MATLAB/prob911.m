function xdot = prob911(t,x)
xdot = zeros(2,1);
xdot(1) = 3*x(1) - 2*x(2);
xdot(2) = 2*x(1) - 2*x(2);