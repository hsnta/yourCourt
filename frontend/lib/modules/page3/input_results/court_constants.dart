import 'package:flutter/material.dart';
import 'package:frontend/modules/page3/input_results/model/court_part_model.dart';
import 'package:path_drawing/path_drawing.dart';

final Map<String, String> partToPathMap = {
  "Middle 3 points":
      "M 246.39286,280.68533 233.36411,461.3565 438.41972,460.8081 425.63466,280.49756 c 0,0 -38.16527,19.33712 -87.3274,21.48029 -36.01138,1.56988 -73.2826,-13.02545 -86.70831,-18.9054 -3.34169,-1.46353 -5.20609,-2.38712 -5.20609,-2.38712 z",
  "Lay-ups":
      "m 259.79697,31.970664 -0.36295,75.758536 -4.66266,59.25926 162.9082,-1.73034 -3.89041,-57.52892 -0.83328,-75.758536 z",
  "Middle 2 points":
      "m 254.78292,166.65322 -8.37516,114.09525 c 0,0 36.52751,19.46642 84.62341,20.97948 48.0959,1.51307 94.69387,-20.8411 94.69387,-20.8411 l -7.90937,-115.72628 z",
  "Left Corner 2 points":
      "M 140.17877,157.69053 259.52702,107.7292 259.65557,31.970664 H 129.86332 c 0,0 -0.20943,55.548578 1.68282,82.508366 1.42672,20.32729 8.63263,43.2115 8.63263,43.2115 z",
  "Right Corner 2 points":
      "M 532.37898,156.93211 413.34378,107.7292 412.78876,31.970664 H 542.7398 c 0,0 -1.05463,29.217202 -1.01325,74.834516 0.0211,23.20767 -9.34757,50.12693 -9.34757,50.12693 z",
  "Left Corner 3 points":
      "M 129.96605,31.970664 H 79.068137 L 79.032847,157.0351 h 61.111933 c 0,0 -7.4816,-28.08563 -8.6406,-53.2689 -1.50399,-32.679449 -1.53813,-71.795536 -1.53813,-71.795536 z",
  "Right Corner 3 points":
      "m 542.24865,31.970664 h 52.32017 l 0.45501,125.064436 h -62.70689 c 0,0 8.99071,-34.59756 9.12681,-51.12754 0.57846,-70.258014 0.8049,-73.936896 0.8049,-73.936896 z",
  "Right Wing 3 points":
      "m 425.43843,280.73121 13.04195,180.06355 156.66589,-0.42025 -0.293,-303.33941 h -63.09774 c 0,0 -0.64269,19.54978 -33.23151,63.80642 -32.58883,44.25664 -73.08559,59.88965 -73.08559,59.88965 z",
  "Left Wing 3 points":
      "M 233.5386,461.06184 H 79.498555 L 79.032847,157.0351 h 61.170213 c 0,0 6.48307,30.32659 33.07111,64.763 30.37631,39.34291 72.96127,59.14839 72.96127,59.14839 l -5.91234,83.87156 -5.98429,84.89208 z",
  // "Othser Half":
  //     "m 79.456615,460.89374 0.201137,429.4559 514.683478,-0.64122 0.85597,-429.62074 z",
  "Left Wing 2 points":
      "M 140.33303,157.44629 259.19872,107.7292 246.1673,281.53412 c 0,0 -35.7237,-18.25493 -66.87858,-52.30962 -27.90851,-30.50614 -38.95569,-71.77821 -38.95569,-71.77821 z",
  "Right Wing 2 points":
      "m 413.34378,107.7292 119.03671,49.11822 c 0,0 -7.00651,33.65488 -39.323,71.23269 -32.3165,37.57781 -67.67575,52.73415 -67.67575,52.73415 z",
};

const double translateX = 11.99959 - 60;
const double translateY = 51.0;

const int courtPictureWidth = 580;
const int courtPictireHeight = 580;

Path calculatePath(String partName, Size size) {
  // print(size);
  final double scaleX = size.width / courtPictureWidth;
  final double scaleY = size.height / courtPictireHeight;
  Path path = parseSvgPathData(partToPathMap[partName]!);
  path = path.transform(Matrix4.identity().scaled(scaleX, scaleY).storage);
  path = path.shift(Offset(translateX * scaleX, translateY * scaleY));
  return path;
}

const Offset slashOffset = Offset(-8, 25);
const Offset denominatorOffset = Offset(-15, 40);

List<CourtPartModel> getMockList() {
  final List<CourtPartModel> models = [];
  models.add(CourtPartModel("Middle 3 points", [5, 10], false, 15));
  models.add(CourtPartModel("Lay-ups", [5, 10], true, 15));
  models.add(CourtPartModel("Middle 2 points", [5, 10], false, 15));
  models.add(CourtPartModel("Left Corner 2 points", [5, 10], true, 15));
  models.add(CourtPartModel("Right Corner 2 points", [5, 10], false, 15));
  models.add(CourtPartModel("Left Corner 3 points", [5, 10], false, 15));
  models.add(CourtPartModel("Right Corner 3 points", [5, 10], false, 15));
  models.add(CourtPartModel("Right Wing 3 points", [5, 10], false, 15));
  models.add(CourtPartModel("Left Wing 3 points", [5, 10], false, 15));
  models.add(CourtPartModel("Left Wing 2 points", [5, 10], false, 15));
  models.add(CourtPartModel("Right Wing 2 points", [5, 10], false, 15));
  return models;
}
