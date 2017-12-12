#include "CircularDLL.h"
#include <iostream>
#include <string>
#include <fstream>
#include <ctime>
#include <sstream>

using namespace std;

/* Imports a new file and add the names into the list
 *
 */
void imp(CircularDLL& list, string fname)
{
	ifstream infile(fname);
	string name;
	while (infile >> name){
		if (list.insertTail(name)){
			cout << name << " was added to the list!" << endl;
		}
		else cout << name << " already exists in the list!" << endl;
	}
}

int main(){
	CircularDLL link;
	int a = 0;
	while (a == 0){
		cout << "1. Import a file containing names to add to the list\n"
			<< "2. Display the current list of names with indices\n"
			<< "3. Prepend a name manually to the list\n"
			<< "4. Append a name manually to the list\n"
			<< "5. Append several names simultaneously to the list\n"
			<< "6. Remove a node at a given index\n"
			<< "7. Randomly shuffle the list\n"
			<< "8. Clear the list\n"
			<< "9. Quit\n";
		int option;
		cin >> option;
		if (option == 1){
			cout << "Enter the name of the file: ";
			string fname;
			cin >> fname;
			imp(link, fname);
			cout << endl;
		}
		else if (option == 2){
			if (link.size() != 0){
				for (int i = 0; i < link.size(); i++){
					cout << i << " - " << link.at(i) << endl;
				}
				cout << endl;
			}
			else cout << "List is empty!\n" << endl;
		}
		else if (option == 3){
			cout << "Enter a name to prepend: ";
			string name;
			cin >> name;
			if (link.insertHead(name)){
				cout << name << " was added to the list!\n" << endl;
			}
			else cout << name << " already exists in the list!\n" << endl;
			cout << endl;
		}
		else if (option == 4){
			cout << "Enter a name to append: ";
			string name;
			cin >> name;
			if (link.insertTail(name)){
				cout << name << " was added to the list!\n" << endl;
			}
			else cout << name << " already exists in the list!\n" << endl;
		}
		else if (option == 5){
			cout << "Enter the names to append: ";
			string names;
			cin.ignore();
			getline(cin, names);
			stringstream ss(names);
			string name;
			while (ss >> name){
				if (link.insertTail(name)){
					cout << name << " was added to the list!" << endl;
				}
				else cout << name << " already exists in the list!" << endl;
			}
			cout << endl;
		}
		else if (option == 6){
			cout << "Enter index: ";
			int index;
			cin >> index;
			string name = link.at(index);
			if (link.removeAt(index)){
				cout << name << " was removed from the list!\n" << endl;
			}
			else cout << "Index invalid!\n" << endl;
		}
		else if (option == 7){
			if (link.size() != 0){
				link.shuffle();
				cout << "List has been shuffled!\n" << endl;
			}
			else cout << "List is empty!\n" << endl;
		}
		else if (option == 8){
			link.clear();
			cout << "List cleared!\n" << endl;
		}
		else if (option == 9){
			a++;
			cout << "Bye!" << endl;
		}
	}

	system("pause");
	return 0;
}
