function phasportf1(equations, timespan, plotrange, solver, parameter)
clf;
axis(plotrange);
hold on;
button = 1;
while button == 1;
    [xinit(1), xinit(2), button] = ginput(1);
    if button ~= 1 break; end;
    [T,Y] = feval(solver, equations, timespan, xinit, [ ], parameter);
    plot(Y(:,1),Y(:,2));
    [T,Y] = feval(solver, equations, -timespan, xinit, [ ], parameter);
    plot(Y(:,1),Y(:,2));
end;