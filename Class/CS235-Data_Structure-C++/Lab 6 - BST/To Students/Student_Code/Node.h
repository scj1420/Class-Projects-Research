//
//  Node.h
//  Lab 6
//
//  Created by Seong-Eun Cho on 6/3/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef Node_h
#define Node_h
#include <iostream>
#include "NodeInterface.h"
using namespace std;

class Node : public NodeInterface{
    friend class BST;
private:
    int data;
    Node* left;
    Node* right;
public:
    Node(const int& the_data, Node* left_val, Node* right_val);
    ~Node(){}
    int getData();
    Node* getLeftChild();
    Node* getRightChild();
    
};


#endif /* Node_h */
