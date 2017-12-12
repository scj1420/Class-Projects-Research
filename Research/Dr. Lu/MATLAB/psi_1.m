function y = psi_1(x)
y = zeros(size(x));

region1 = x <= -1;
y(region1) = x(region1) + 2;

region2 = (x > -1) & (x < 1);
y(region2) = -x(region2);

region3 = x >= 1;
y(region3) = x(region3) - 2;