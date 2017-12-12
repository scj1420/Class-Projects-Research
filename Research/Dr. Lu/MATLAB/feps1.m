function xdot = feps1(t, x)
xdot = zeros(2,1);
z = psi_1(x(1));
xdot(1) = (x(2) - z);
xdot(2) = -x(1);
