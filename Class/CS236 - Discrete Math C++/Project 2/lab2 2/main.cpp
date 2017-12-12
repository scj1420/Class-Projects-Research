//
//  main.cpp
//  Project 2
//
//  Created by Seong-Eun Cho on 6/30/16.
//  Copyright © 2016 Seong-Eun Cho. All rights reserved.
//

#include <iostream>
#include "Scanner.h"
#include "Parser.h"

int main(int argc, const char* argv[]) {
    //string infile, outfile;
    //cin >> infile >> outfile;
    Scanner scan;
    scan.importFile(argv[1]);
    //scan.exportFile(argv[2]);
    Parser parse(scan, argv[2]);
    parse.datalogProgram();
    parse.print();
    
    return 0;
}
