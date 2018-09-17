//
//  BST.cpp
//  Lab 6
//
//  Created by Seong-Eun Cho on 6/3/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include "BST.h"
using namespace std;

BST::BST() : root(NULL) {}

NodeInterface* BST::getRootNode(){
    return root;
}

bool BST::add(int data){
    return add(this->root, data);
}
bool BST::add(Node*& local_root, const int data){
    if (local_root == NULL){
        local_root = new Node(data, NULL, NULL);
        return true;
    }
    else{
        if (data < local_root->data) return add(local_root->left, data);
        else if (data > local_root->data) return add(local_root->right, data);
        else return false;
    }
}

bool BST::remove(int data){
    return remove(this->root, data);
}

bool BST::remove(Node*& local_root, const int& data){
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
            return true;
        }
    }
}

void BST::replace_parent(Node*& old_root, Node*& local_root){
    if (local_root->right != NULL){
        replace_parent(old_root, local_root->right);
    }
    else{
        old_root->data = local_root->data;
        old_root = local_root;
        local_root = local_root->left;
    }
}

void BST::clear(){
    while (root != NULL){
        remove(root->data);
    }
}

void BST::print(){
    print(this->root);
}
void BST::print(Node* old_root){
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
        cout << old_root->data << " ";
    }
}

int BST::getRoot(){
    return root->data;
}