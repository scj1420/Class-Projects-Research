#include "Factory.h"
// You may put your include statements here
#include "GPA.h"
using namespace std;

/*
	You may modify this document
*/
//=======================================================================================
/*
	getGPA()

	Creates and returns an object whose class extends GPAInterface.
	This should be an object of a class you have created.

	Example: If you made a class called "GPA", you might say, "return new GPA();".
*/
GPAInterface * Factory::getGPA() {
    return new GPA();
}
//=======================================================================================
