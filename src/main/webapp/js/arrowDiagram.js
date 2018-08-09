var svg = d3.select("svg"),
    margin = {top: 100, right: 0, bottom: 20, left:50},
    width = +svg.attr("width") - margin.left - margin.right,
    height = +svg.attr("height") - margin.top - margin.bottom,
    gdiv = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
var formatNumber = d3.format(".1f");


//domain 必须包括两个及两个以上的时间对象
var x = d3.scaleTime()
    .domain([new Date(2010, 1, 1), new Date(2018, 9, 1)])
    .range([0, width]);


var y = d3.scaleLinear()
    .domain([0, 1])
    .range([height, 0]);


var xAxis = d3.axisBottom(x)
    .ticks(d3.timeYear);


var yAxis = d3.axisRight(y)
    .tickSize(width)
    .tickFormat(function(d) {
        var s = formatNumber(d / 1);
        return this.parentNode.nextSibling
            ? "\xa0" + s
            : "\xa0" + s + " ";
    });

gdiv.append("g")
    .attr("transform", "translate(0," + height + ")")
    .call(customXAxis);

gdiv.append("g")
    .call(customYAxis);//

function customXAxis(g) {
    g.call(xAxis);//调用x轴
    g.select(".domain").remove();//////
}

function customYAxis(g) {
    g.call(yAxis);
    console.log(g.select(".domain"));
    // g.select(".domain").remove();
    g.selectAll(".tick:not(:first-of-type) line").attr("stroke", "#777").attr("stroke-dasharray", "2,2");
    g.selectAll(".tick text").attr("x", 4).attr("dy", -4);
}
console.log(gdiv);
