function xdot = vdp1000(t,x)
xdot = zeros(2,1);
xdot(1) = x(2);
xdot(2) = 1000*(1-x(1)^2)*x(2) - x(1);