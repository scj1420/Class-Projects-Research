//
//  Node.h
//  Midterm
//
//  Created by Seong-Eun Cho on 5/26/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#ifndef Node_h
#define Node_h
#include <iostream>
#include <string>

struct Node {
	std::string data;
	Node* next;
	Node* previous;
	Node(std::string data, Node* next, Node* previous){
		this->data = data;
		this->next = next;
		this->previous = previous;
	}
};

#endif /* Node_h */
