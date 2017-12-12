//
//  CircularDLL.cpp
//  Midterm
//
//  Created by Seong-Eun Cho on 5/26/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include <stdio.h>
#include "CircularDLL.h"
#include <iostream>
#include <string>
#include <ctime>

using namespace std;

CircularDLL::CircularDLL() : head(NULL), tail(NULL), length(0){}

bool CircularDLL::find(string name){
	Node* temp = head;
	if (size() != 0){
		if (size() != 1){
			if (temp->data != name){
				temp = temp->next;
			}
			else return true;

			while (temp->data != name && temp != head){
				temp = temp->next;
			}
			if (temp != head) return true;
			else return false;
		}
		else{
			if (temp->data == name) return true;
			else return false;
		}
	}
	else return false;
}

bool CircularDLL::insertHead(string name){
	if (!find(name)){
		if (size() != 0){
			head->previous = new Node(name, head, tail);
			head = head->previous;
			tail->next = head;
			length++;
			return true;
		}
		else{
			head = new Node(name, NULL, NULL);
			head->next = head;
			head->previous = head;
			tail = head;
			length++;
			return true;
		}
	}
	else return false;
}

bool CircularDLL::insertTail(string name){
	if (!find(name)){
		if (size() != 0){
			tail->next = new Node(name, head, tail);
			tail = tail->next;
			head->previous = tail;
			length++;
			return true;
		}
		else{
			head = new Node(name, NULL, NULL);
			head->next = head;
			head->previous = head;
			tail = head;
			length++;
			return true;
		}
	}
	else return false;
}

bool CircularDLL::insertAt(string name, int index){
	if (!find(name) && size() != 0){
		if (index != 0){
			Node* temp = head;
			if (index >= 0){
				for (int i = 0; i < index; i++){
					temp = temp->next;
				}
				temp->previous = new Node(name, temp, temp->previous);
				temp->previous->previous->next = temp->previous;
				length++;
				if (temp == head){
					tail = temp->previous;
				}
			}
			else{
				for (int i = 0; i > index; i--){
					temp = temp->previous;
				}
				temp->next = new Node(name, temp->next, temp);
				temp->next->next->previous = temp->next;
				length++;
				if (temp == tail){
					tail = temp->next;
				}
			}
			return true;
		}
		else{
			insertHead(name);
			return true;
		}
	}
	else return false;
}

bool CircularDLL::removeHead(){
	if (size() != 0){
		if (size() != 1){
			Node* temp = head;
			head = head->next;
			tail->next = head;
			head->previous = tail;
			delete temp;
			length--;
			return true;
		}
		else{
			Node* temp = head;
			head = NULL;
			tail = NULL;
			delete temp;
			length--;
			return true;
		}
	}
	else return false;
}

bool CircularDLL::removeTail(){
	if (size() != 0){
		if (size() != 1){
			Node* temp = tail;
			tail = tail->previous;
			head->previous = tail;
			tail->next = head;
			delete temp;
			length--;
			return true;
		}
		else{
			Node* temp = head;
			head = NULL;
			tail = NULL;
			delete temp;
			length--;
			return true;
		}
	}
	else return false;
}

bool CircularDLL::removeAt(int index){
	if (size() != 0){
		Node* temp = head;
		if (index >= 0){
			for (int i = 0; i < index; i++){
				temp = temp->next;
			}
		}
		else{
			for (int i = 0; i > index; i--){
				temp = temp->previous;
			}
		}
		temp->next->previous = temp->previous;
		temp->previous->next = temp->next;
		if (temp == head){
			head = head->next;
		}
		else if (temp == tail){
			tail = tail->previous;
		}
		delete temp;
		length--;
		if (length == 0){
			head = NULL;
			tail = NULL;
		}
		return true;
	}
	else return false;
}

void CircularDLL::clear(){
	Node* temp;
	while (head != tail)
	{
		temp = head;
		head = head->next;
		delete temp;
	}
	delete head;
	head = NULL;
	tail = NULL;
	length = 0;
}

int CircularDLL::size(){
	return length;
}

string CircularDLL::at(int index){
	if (size() != 0){
		Node* temp = head;
		if (index >= 0){
			for (int i = 0; i < index; i++){
				temp = temp->next;
			}
			return temp->data;
		}
		else{
			for (int i = 0; i > index; i--){
				temp = temp->previous;
			}
			return temp->data;
		}
	}
	else return "empty";
}

void CircularDLL::shuffle(){
	srand(time(0));
	for (int i = 0; i < size(); i++){
		int j = rand() % size();
		Node* t = head;
		Node* z = head;
		string n = at(i);
		for (int a = 0; a < i; a++){
			t = t->next;
		}
		for (int a = 0; a < j; a++){
			z = z->next;
		}
		t->data = z->data;
		z->data = n;
	}
}

CircularDLL::~CircularDLL(){
	clear();
}
