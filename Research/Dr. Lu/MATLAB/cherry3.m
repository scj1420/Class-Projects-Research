function xdot = cherry3(t,x)
xdot = zeros(2,1);
xdot(1) = x(2);
xdot(2) = -(t)*sin(x(1));