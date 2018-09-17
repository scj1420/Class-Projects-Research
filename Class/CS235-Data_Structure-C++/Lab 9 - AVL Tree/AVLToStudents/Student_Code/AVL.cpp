//
//  AVL.cpp
//  Lab 9
//
//  Created by Seong-Eun Cho on 6/8/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include <stdio.h>
#include "AVL.h"
using namespace std;

AVL::AVL() : root(NULL) {}

NodeInterface* AVL::getRootNode(){
    return root;
}

bool AVL::add(int data){
    return add(this->root, data);
}
bool AVL::add(Node*& local_root, const int data){
    if (local_root == NULL){
        local_root = new Node(data, NULL, NULL);
        updateAll();
        balanceTree(this->root);
        return true;
    }
    else{
        if (data < local_root->data) return add(local_root->left, data);
        else if (data > local_root->data) return add(local_root->right, data);
        else return false;
    }
}

bool AVL::remove(int data){
    return remove(this->root, data);
}
bool AVL::remove(Node*& local_root, const int& data){
    if (local_root == NULL) return false;
    else{
        if (data < local_root->data) return remove(local_root->left, data);
        else if (data > local_root->data) return remove(local_root->right, data);
        else{
            Node* old_root = local_root;
            if (local_root->left == NULL){
                local_root = local_root->right;
            }
            else if (local_root->right == NULL){
                local_root = local_root->left;
            }
            else {
                replace_parent(old_root, old_root->left);
            }
            delete old_root;
            updateAll();
            balanceTree(this->root);
            return true;
        }
    }
}

void AVL::replace_parent(Node*& old_root, Node*& local_root){
    if (local_root->right != NULL){
        replace_parent(old_root, local_root->right);
    }
    else{
        old_root->data = local_root->data;
        old_root = local_root;
        local_root = local_root->left;
    }
}

void AVL::clear(){
    while (root != NULL){
        remove(root->data);
    }
}

void AVL::print(){
    print(this->root);
}
void AVL::print(Node* old_root){
    if (old_root == NULL){
        cout << "empty" << endl;
    }
    else{
        if (old_root->left != NULL){
            print(old_root->left);
        }
        if (old_root->right != NULL){
            print(old_root->right);
        }
        cout << old_root->data << "(" << old_root->height << ", " << old_root->balance << ", " << old_root->balanceType << ")" << " ";
    }
}

int AVL::getRoot(){
    return root->data;
}

void AVL::rotateRight(Node*& local_root){
    Node* temp = local_root->left;
    local_root->left = temp->right;
    temp->right = local_root;
    local_root = temp;
}

void AVL::rotateLeft(Node*& local_root){
    Node* temp = local_root->right;
    local_root->right = temp->left;
    temp->left = local_root;
    local_root = temp;
}

void AVL::updateHeight(Node*& local_root){
    if (local_root != NULL){
        local_root->height = countHeight(local_root);
    }
    else return;
    updateHeight(local_root->left);
    updateHeight(local_root->right);
}
int AVL::countHeight(Node *&local_root){
    if (local_root->right == NULL && local_root->left == NULL){
        return 1;
    }
    else if (local_root->right != NULL && local_root->left == NULL){
        return 1 + countHeight(local_root->right);
    }
    else if (local_root->right == NULL && local_root->left != NULL){
        return 1 + countHeight(local_root->left);
    }
    else{
        if (countHeight(local_root->right) > countHeight(local_root->left)){
            return 1 + countHeight(local_root->right);
        }
        else return 1 + countHeight(local_root->left);
    }
}

void AVL::updateBalance(Node *&local_root){
    if (local_root != NULL){
        local_root->balance = balance(local_root);
    }
    else return;
    updateBalance(local_root->left);
    updateBalance(local_root->right);
}
int AVL::balance(Node*& local_root){
    if (local_root->right == NULL && local_root->left == NULL){
        return 0;
    }
    else if (local_root->right != NULL && local_root->left == NULL){
        return local_root->right->height;
    }
    else if (local_root->right == NULL && local_root->left != NULL){
        return -(local_root->left->height);
    }
    else return local_root->right->height - local_root->left->height;
}

void AVL::updateBalanceType(Node*& local_root){
    if (local_root != NULL){
        local_root->balanceType = balanceType(local_root);
    }
    else return;
    updateBalanceType(local_root->left);
    updateBalanceType(local_root->right);
}
string AVL::balanceType(Node*& local_root){
    if (local_root != NULL){
        if (local_root->balance < 2 && local_root->balance > -2){
            return "balanced";
        }
        else if (local_root->balance >= 2){
            if (local_root->right->balance > 0){
                return "RR";
            }
            else return "RL";
        }
        else{
            if (local_root->left->balance < 0){
                return "LL";
            }
            else return "LR";
        }
    }
    else return "empty";
}

void AVL::balanceTree(Node*& local_root){
    if (local_root != NULL){
        balanceTree(local_root->left);
        balanceTree(local_root->right);
        balanceNode(local_root);
        updateAll();
    }
    else return;
}
void AVL::balanceNode(Node*& local_root){
    if (local_root->balanceType == "balanced" || local_root->balanceType == "empty"){
        return;
    }
    else if (local_root->balanceType == "RR"){
        rotateLeft(local_root);
    }
    else if (local_root->balanceType == "RL"){
        rotateRight(local_root->right);
        rotateLeft(local_root);
    }
    else if (local_root->balanceType == "LL"){
        rotateRight(local_root);
    }
    else {
        rotateLeft(local_root->left);
        rotateRight(local_root);
    }
}

void AVL::updateAll(){
    updateHeight(this->root);
    updateBalance(this->root);
    updateBalanceType(this->root);
}