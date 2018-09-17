//
//  main.cpp
//  Lab 9
//
//  Created by Seong-Eun Cho on 6/8/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include <iostream>
#include "AVL.h"

int main() {
    AVL tree;
    int i = 0;
    while (i == 0){
        int option;
        cin >> option;
        if (option == 1){
            int a;
            cin >> a;
            tree.add(a);
        }
        else if (option == 2){
            int a;
            cin >> a;
            tree.remove(a);
        }
        else if (option == 3){
            cout << tree.getRoot() << endl;
        }
        else if (option == 4){
            tree.print();
            cout << endl;
        }
        else if (option == 5){
            tree.clear();
        }
        else if (option == 6){
            //cout << tree.balanceType() << endl;
        }
    }
    
    return 0;
}
