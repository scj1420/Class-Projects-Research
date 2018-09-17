//
//  AVL.h
//  Lab 9
//
//  Created by Seong-Eun Cho on 6/8/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef AVL_h
#define AVL_h
#include "AVLInterface.h"
#include "Node.h"
using namespace std;

class AVL : public AVLInterface{
public:
    AVL();
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
    void rotateRight(Node*& local_root);
    void rotateLeft(Node*& local_root);
    int countHeight(Node*& local_root);
    void updateHeight(Node*& local_root);
    int balance(Node*& local_root);
    void updateBalance(Node*& local_root);
    void updateBalanceType(Node*& local_root);
    string balanceType(Node*& local_root);
    void balanceNode(Node*& local_root);
    void balanceTree(Node*& local_root);
    void updateAll();
};

#endif /* AVL_h */
