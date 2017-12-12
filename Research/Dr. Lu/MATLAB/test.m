function y=test(x)
y = zeros(size(x)); % Preallocating enough memory for y

region1 = x<2; % First interval
y(region1) = x(region1).^2;

region2 = (2<=x) & (x<=7); % Second interval
y(region2) = x(region2) + 2;

region3 = (8<x); % Third interval
y(region3) = x(region3).^3-6*x(region3).^2;