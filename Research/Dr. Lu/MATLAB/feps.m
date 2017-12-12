function xdot = feps(t, x, flags, mu)
xdot = zeros(2,1);
z = psi_1(x(1));
xdot(1) = (x(2) - z)/mu;
xdot(2) = -x(1) * mu;
