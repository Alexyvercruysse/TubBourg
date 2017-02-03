package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Line;

import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public interface LineRepository {

    List<Line> listLines();

    Line getLineById(int idLine);

    Line getLineByName(String nameLine);

    void addLine(Line line);
}
