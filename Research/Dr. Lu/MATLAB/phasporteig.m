function phasporteig(equations, timespan, plotrange, solver, eig1, eig2, eigs, const)
clf;
axis(plotrange);
hold on;
button = 1;
x = plotrange(1:2);
y1 = eig1(2)/eig1(1)*x;
plot(x, y1, 'r--', 'LineWidth',2);
y2 = eig2(2)/eig2(1)*x;
plot(x, y2, 'b--', 'LineWidth',2);
e1 = horzcat('r1 = ', num2str(eigs(1)));
e2 = horzcat('r2 = ', num2str(eigs(2)));
legend(e1, e2);
while button == 1,
    [xinit(1), xinit(2), button] = ginput(1);
    if button ~= 1 break; end;
    [T,Y] = feval(solver,equations,timespan,xinit,[],const);
    plot(Y(:,1),Y(:,2));
    [T,Y] = feval(solver,equations,-timespan,xinit,[],const);
    plot(Y(:,1),Y(:,2));
end;