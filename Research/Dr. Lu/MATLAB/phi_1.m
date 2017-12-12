function y = phi_1(x)
y = zeros(size(x));

region1 = (x>0)&(x<pi);
y(region1) = 1;

region2 = (x > pi) & (x < 2*pi);
y(region2) = -1;

region3 = x == 0;
y(region3) = 0;