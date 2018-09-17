/*
 LAB 8 --- CAR DEALERSHIP
 
 Test Cases -
 
 Test 1
 Input: 1 - 2 - 3 - Mustang, Black, 2500 - 1 - 2 - 3 - Mustang, Blue, 1500 - 4 - Toyota - 2 - 4 - Mustang - 1 - 2 - 8
 Expected Output: Inventory Empty - $10000 - Car purchased - Mustang, Black, 2500 - 7500 - Car already owned - Car not owned - 2 - 7500 - Car sold - Inventory Empty - $10000 - terminate
 Actual Output: Inventory Empty - $10000 - Car purchased - Mustang, Black, 2500 - 7500 - Car already owned - Car not owned - 2 - 7500 - Car sold - Inventory Empty - $10000 - terminate
 
 Test 2
 Input: 3 - Mustang, Black, 20000 - 2 - 3 - Mustang, Blue, 4500 - 5 - Toyota - 5 - Mustang - Blue - 5 - Mustang - Black - 1 - 2 - 4 - Mustang - 1 - 2 - 8
 Expected Output: Can't afford - $10000 - Car purchased - Car not owned - Car already in that color - Car painted - Mustang, Black, 5500, 5500, Car sold - Inventory Empty - 11000 - terminate
 Actual Output: Can't afford - $10000 - Car purchased - Car not owned - Car already in that color - Car painted - Mustang, Black, 5500, 5500, Car sold - Inventory Empty - 11000 - terminate
 
 
 Test 3
 Input: 6 - cars1.txt - 1 - 2 - 6 - cars2.txt - 1 - 2 - 7 - cars3.txt - 8
 Expected Output: File loaded - Jalopy, Rustbucket, and Lemon - $10529.23 - File loaded - Jalopy, Rustbucket, Lemon, Navy, Kidney, Refried, Garbanzo, Black, Edamame, Lima, Pinto - $70244.66 - File saved - terminate - cars3.txt contains: 70244.66 with all the mentioned cars.
 Actual Output: File loaded - Jalopy, Rustbucket, and Lemon - $10529.23 - File loaded - Jalopy, Rustbucket, Lemon, Navy, Kidney, Refried, Garbanzo, Black, Edamame, Lima, Pinto - $70244.66 - File saved - terminate - cars3.txt contains: 70244.66 with all the mentioned cars.
 
 */



#include <iomanip>
#include <vector>
#include <fstream>
#include "Car.h"

double INITIAL_BALANCE = 10000;

void print(vector<Car> inventory)
{
    if (inventory.size() == 0){
        cout << "Inventory Empty\n\n";
    }
    else{
        for (int i = 0; i < inventory.size(); i++){
            cout << inventory[i].toString() << endl;
        }
    }
}

int find(vector<Car> inventory, string car)
{
    int j = -1;
    for (int i = 0; i < inventory.size(); i++){
        if (inventory[i].getName() == car) { j = i; }
    }
    return j;
}

void buycar(vector<Car>& inventory, Car buy, double& balance)
{
    int findex = find(inventory, buy.getName());
    if (findex == -1){
        inventory.push_back(buy);
        balance -= buy.getPrice();
        cout << "Car Purchased\n" << endl;
    }
    else { cout << "You already own this car!\n" << endl; }
}

void sellcar(vector<Car>& inventory, string sell, double& balance)
{
    int findex = find(inventory, sell);
    if (findex != -1){
        for (int i = 0; i < inventory.size(); i++){
            if (inventory[i].getName() == sell){
                Car carsold = inventory[i];
                balance += carsold.getPrice();
                if (i < inventory.size() - 1){
                    while (i < inventory.size() - 1){
                        inventory[i] = inventory[i + 1];
                        i++;
                    }
                }
            }
        }
        inventory.pop_back();
        
        cout << "Car sold\n" << endl;
    }
    else{
        cout << "Car not owned\n" << endl;
    }
    
    
}

void imp(vector<Car>& inventory, string name, double& balance)
{
    ifstream infile(name);
    double money;
    infile >> money;
    balance += money;
    string carname;
    string color;
    double price;
    while (infile >> carname >> color >> price)
    {
        Car car1(carname, color, price);
        inventory.push_back(car1);
    }
}

void exp(vector<Car> inventory, string name, double balance)
{
    ofstream outfile(name);
    
    outfile << fixed << setprecision(2) << balance << endl;
    for (int i = 0; i < inventory.size(); i++){
        outfile << inventory[i].getName() << " " << inventory[i].getColor() << " " << inventory[i].getPrice() << endl;
    }
    
    outfile.close();
}

int main()
{
    int a = 0;
    int option1;
    double balance = INITIAL_BALANCE;
    vector<Car> inventory;
    
    while (a == 0){
        cout << "Please select an option:" << endl
        << "1 - Show Current Inventory" << endl
        << "2 - Show Current Balance" << endl
        << "3 - Buy a Car" << endl
        << "4 - Sell a Car" << endl
        << "5 - Paint a Car" << endl
        << "6 - Load File" << endl
        << "7 - Save File" << endl
        << "8 - Quit Program\n" << endl;
        
        cin >> option1;
        if (option1 == 1){
            print(inventory);
        }
        if (option1 == 2){
            cout << fixed << setprecision(2) << "Your balance is $" << balance << "\n\n";
        }
        if (option1 == 3){
            string name, color;
            double price;
            cout << "Name: "; cin >> name;
            cout << "Color: "; cin >> color;
            cout << "Price: "; cin >> price;
            if (price > balance){
                cout << "You can't affort this car!\n\n";
            }
            else{
                Car buy(name, color, price);
                buycar(inventory, buy, balance);
            }
            
        }
        if (option1 == 4){
            string sell;
            cout << "Car to sell: "; cin >> sell;
            sellcar(inventory, sell, balance);
        }
        if (option1 == 5){
            string name;
            string newcolor;
            cout << "Car name: "; cin >> name;
            if (find(inventory, name) == -1){
                cout << "Car not owned\n" << endl;
            }
            else{
                cout << "New color: "; cin >> newcolor;
                if (inventory[find(inventory, name)].getColor() == newcolor){
                    cout << "The car is already in that color!\n\n";
                }
                else{
                    inventory[find(inventory, name)].paint(newcolor);
                    cout << "Car painted " << newcolor << ".\n\n";
                }
            }
        }
        if (option1 == 6){
            string ifname;
            cout << "Enter file name: "; cin >> ifname;
            imp(inventory, ifname, balance);
            cout << "File loaded\n\n";
        }
        if (option1 == 7){
            string ofname;
            cout << "Enter file name: "; cin >> ofname;
            exp(inventory, ofname, balance);
            cout << "File saved\n\n";
        }
        if (option1 == 8){
            a++;
        }
    }
    
    
    
    return 0;
}
