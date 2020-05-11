# -*- coding: utf-8 -*-
"""
Created on Mon May 11 07:33:50 2020

@author: TOSHIBA
"""


import sys
import numpy as np
import pandas as pd

dataset = pd.read_csv('testfile.csv')

#KOJI DEVELOPER JE RADIO KOLIKO FAJLOVA

dev_files = dataset.iloc[:,[0,3,4]]
#dev_files = dev_files.drop_duplicates()

dev_files = dev_files.drop(['line'],axis=1)
dev_files = dev_files.groupby(['developer','file']).size().reset_index(name='lines')

indexNames = dev_files.loc[dev_files['lines']> 800].index
dev_files.drop(indexNames , inplace=True)


dev_lines = dev_files.iloc[:,[0,2]]
dev_lines = dev_lines.groupby(['developer']).sum().reset_index()

res = dev_lines.iloc[:,1:]
res = res.div(res.sum(axis=0), axis=1)
res['lines']=res['lines']*100
dev_lines['perc']=res['lines']

res = dev_lines.loc[[dev_lines.lines.argmax(),dev_lines.lines.argmin()]]

print(res)














