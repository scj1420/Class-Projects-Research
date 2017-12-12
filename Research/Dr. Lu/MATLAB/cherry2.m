function xdot = cherry2(t,x)
f = @(t) phi_1(mod(t, 2*pi));
xdot = zeros(2,1);
xdot(1) = x(2);
xdot(2) = -f(x(1));