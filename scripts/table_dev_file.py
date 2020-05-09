# -*- coding: utf-8 -*-
"""
Created on Sun May  3 12:42:38 2020

@author: TOSHIBA
"""

import sys
import numpy as np
import pandas as pd

dataset = pd.read_csv('testfile.csv')

#KOJI DEVELOPER JE RADIO KOLIKO FAJLOVA

dev_files = dataset.iloc[:,[0,3]]
dev_files = dev_files.drop_duplicates()

dev_files = dev_files.drop(['file'],axis=1)
dev_files = dev_files.groupby(['developer']).size().reset_index(name='files')
print(dev_files)