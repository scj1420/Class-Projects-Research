function plotfparam(equations, timespan, plotrange, solver, parameter)
clf;
axis(plotrange);
hold on;
button = 1;
while button == 1;
    [xinit(1), xinit(2), button] = ginput(1);
    if button ~= 1 break; end;
    [t,x] = feval(solver, equations, timespan, xinit, [ ], parameter);
    plot(t,x(:,1),'r',t,x(:,2),'b-.')
    [t,x] = feval(solver, equations, -timespan, xinit, [ ], parameter);
    plot(t,x(:,1),'r',t,x(:,2),'b-.')
end;