#ifndef _LL_
#define _LL_

#include <iostream>
#include <string>
#include <cstdlib>
#include "LLN.h"
using namespace std;

class LLN;
class LL {
private:

	LLN* head;

public:

	LL();
	~LL();
	void print();
	void add(string d);
	int ct();
	void shellsort();

};


#endif