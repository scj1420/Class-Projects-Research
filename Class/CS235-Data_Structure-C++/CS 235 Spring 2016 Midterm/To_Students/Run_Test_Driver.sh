#!/bin/bash
EXE=dont_run_me

g++ -std=c++11 -o$EXE Student_Code/*.cpp Student_Code/*.h ignoreme.a -g

if (( $? )) ;
then 
	echo Compilation Failed;
	read -p "Press enter to exit";
else 
	chmod 755 $EXE;
	
	if [ "$1" == nv ];
	then
	    ./$EXE
	else
	    valgrind --tool=memcheck --leak-check=yes --max-stackframe=5000000 --show-reachable=yes --suppressions=DONT_DELETE.supp ./$EXE
	fi
	
	rm ./$EXE
	read -p "Press any key to exit..."
fi;
