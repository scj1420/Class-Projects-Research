//
//  CircularDLL.h
//  Midterm
//
//  Created by Seong-Eun Cho on 5/26/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef CircularDLL_h
#define CircularDLL_h
#include <iostream>
#include <string>
#include "CircularDLLInterface.h"
using namespace std;

class CircularDLL : public CircularDLLInterface{
private:
#include "Node.h"
	Node* head;
	Node* tail;
	int length;

public:
	CircularDLL();
	bool find(string name);
	bool insertHead(string name);
	bool insertTail(string name);
	bool insertAt(string name, int index);
	bool removeHead();
	bool removeTail();
	bool removeAt(int index);
	void clear();
	int size();
	string at(int index);
	void shuffle();
	~CircularDLL();
};


#endif /* CircularDLL_h */
