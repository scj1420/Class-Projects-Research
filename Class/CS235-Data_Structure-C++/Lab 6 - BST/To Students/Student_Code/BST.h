//
//  BST.h
//  Lab 6
//
//  Created by Seong-Eun Cho on 6/3/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef BST_h
#define BST_h
#include "BSTInterface.h"
#include "Node.h"
using namespace std;

class BST : public BSTInterface{
public:
    BST();
    NodeInterface* getRootNode();
    bool add(int data);
    bool remove(int data);
    void clear();
    void print();
    int getRoot();
private:
    Node* root;
    bool add(Node*& local_root, const int data);
    bool remove(Node*& local_root, const int& data);
    void replace_parent(Node*& old_root, Node*& local_root);
    void print(Node* old_root);
};

#endif /* BST_h */
