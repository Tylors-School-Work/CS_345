#include <iostream>
#include <string>
#include <cstdlib>
#include "LL.h"
#include "LLN.h"
#include "PG1.h"
using namespace std;

LL::LL() {

	head = nullptr;
}

LL::~LL() {

	delete head;
}

void LL::print() {

	cout << "*****" << endl;
	if (head) head->print();
	cout << "*****" << endl;
}

void LL::add(string d) {

	head = new LLN(d, head);
}

int LL::ct() {

	if (!head) return 0;
	return head->ct();
}

void LL::shellsort() {

	int c = ct();
	if (c <= 1) return;
	for (int diff = c - 1; diff >= 1; diff--) {
		int tdiff = diff;
		while (tdiff % 2 == 0) tdiff /= 2;
		while (tdiff % 3 == 0) tdiff /= 3;
		if (tdiff > 1) continue;
		LLN** arr = new LLN * [diff];
		for (int i = 0; i < diff; i++)
			arr[i] = nullptr;
		head->split(arr, diff, 0);
		for (int i = 0; i < diff; i++)
			arr[i] = arr[i]->mildsort();
		head = rejoin(arr, diff, (c - 1 + diff) % diff, nullptr);
	}
}