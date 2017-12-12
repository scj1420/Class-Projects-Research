function xdot = f6e1sin(t, x, flags, mu)
xdot = zeros(2,1);
if -1 < x(1) < 1
    xdot(1) = x(2) + x(1);
else 
    xdot(1) = x(2) - (x(1) - 2);
end

xdot(2) = -x(1) + sin(t);
