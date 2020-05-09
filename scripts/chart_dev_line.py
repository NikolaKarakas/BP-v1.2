# -*- coding: utf-8 -*-
"""
Created on Sun May  3 20:49:39 2020

@author: TOSHIBA
"""

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
dataset = pd.read_csv('testfile.csv')


# KOLIKO JE LINIJA DEVELOPER NAPISAO  GRAPH

dev_file_lines = dataset.iloc[:,[0,3]]
dev_file_lines.sort_values(by=['developer', 'file'],inplace =True)
dev_file_lines.reset_index(inplace= True,drop = True)
dev_file_lines = dev_file_lines.groupby(['developer','file']).size().reset_index(name='lines')
indexNames = dev_file_lines.loc[dev_file_lines['lines']> 800].index
dev_file_lines.drop(indexNames , inplace=True)

dev_lines = dev_file_lines.iloc[:,[0,2]]
dev_lines = dev_lines.groupby(['developer']).sum().reset_index()
print(dev_lines)

'''labels = dev_lines.developer.values

sizes = dev_lines.lines.values
explode = (0.1, 0.1, 0.1, 0.1,0.1,0.1)  # only "explode" the 2nd slice (i.e. 'Hogs')

fig1, ax1 = plt.subplots()
ax1.pie(sizes, explode=explode, labels=labels, autopct='%1.1f%%',
        shadow=True, startangle=90)
ax1.axis('equal')  # Equal aspect ratio ensures that pie is drawn as a circle.

plt.savefig('scripts/chart_dev_line.png')'''
