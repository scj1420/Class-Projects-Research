function xdot = vdpsin(t,x,flags,mu)
xdot = zeros(2,1);
xdot(1) = x(2);
xdot(2) = mu*(1-x(1)^2)*x(2) - x(1) - 20*sin(2*(pi)*5*t);