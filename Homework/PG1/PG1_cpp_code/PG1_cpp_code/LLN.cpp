#include <iostream>
#include <string>
#include <cstdlib>
#include "LLN.h"
using namespace std;

LLN::LLN(string d, LLN* n) {

	data = d;
	next = n;
}

LLN::~LLN() {

	delete next;
}

void LLN::print() {

	cout << data << endl;
	if (next) next->print();
}

int LLN::ct() {

	if (!next) return 1;
	return 1 + next->ct();
}

void LLN::split(LLN** arr, int len, int wh) {

	/*
	 split takes the linked headed by "this" and splits it into an array arr
	 (whose length is len).  wh indicates the position at which the first
	 node in the list should go.
	*/

}

LLN* LLN::mildsort() {

	/*
	 mildsort sorts the "this" list and returns the new head.
	 mildsort assumes that no node is more than one position out of place
	 and uses this assumption to run very fast.
	*/
}

string LLN::getdata() {

	return data;
}

LLN* LLN::getnext() {

	return next;
}

void LLN::setnext(LLN* n) {

	next = n;
}