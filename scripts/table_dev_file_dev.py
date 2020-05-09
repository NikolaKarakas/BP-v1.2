# -*- coding: utf-8 -*-
"""
Created on Sun May  3 16:55:19 2020

@author: TOSHIBA
"""

# Importing the libraries
import numpy as np
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('testfile.csv')


#BROJ KOLABORACIJA IZMEDJU DEVELOPERA
dev1_file_dev2 = dataset.iloc[:,[0,3]]
dev1_file_dev2 = dev1_file_dev2.drop_duplicates()


c = pd.merge(dev1_file_dev2,dev1_file_dev2, on='file', how= 'inner')

indexNames = c.loc[c['developer_x'] == c['developer_y']].index
c.drop(indexNames , inplace=True)
c.drop(['file'],axis=1)


c= c.groupby(['developer_x','developer_y']).count().reset_index()
for i in range(0, len(c)):
    if c.developer_x.iloc[i] > c.developer_y.iloc[i]:
        temp = c.developer_x.iloc[i]
        c.developer_x.iloc[i] = c.developer_y.iloc[i]
        c.developer_y.iloc[i] = temp
c = c.drop_duplicates()
print(c)
