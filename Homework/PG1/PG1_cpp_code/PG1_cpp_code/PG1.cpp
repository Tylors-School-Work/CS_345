#include <iostream>
#include <string>
#include <cstdlib>
#include "PG1.h"
#include "LL.h"
using namespace std;

int main(int argc, char** argv) {

	LL* list = new LL();
	while (true) {
		string d;
		getline(cin, d);
		if (d == "") break;
		list->add(d);
	}
	list->shellsort();
	list->print();

	delete list;
	return 0;
}

LLN* rejoin(LLN** arr, int len, int wh, LLN* l) {

	/*
	 rejoin takes the lists in arr (whose length is len) and appends them to
	 the beginning of list l.
	 wh indicates the position within arr the element that needs to be the
	 first one attached to l.
	*/
}