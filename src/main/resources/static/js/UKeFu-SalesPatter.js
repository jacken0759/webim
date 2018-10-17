jsPlumb.ready(function () {

    var j = window.j = jsPlumb.getInstance({Endpoint: ["Dot", {radius: 2}],
        Connector:"StateMachine",
        HoverPaintStyle: {stroke: "#32c24d", strokeWidth: 2 },
        ConnectionOverlays: [
            [ "Arrow", {
                location: 1,
                id: "arrow",
                length: 14,
                foldback: 0.8
            } ]
        ],
        Container: "canvas"});

    j.bind("connection", function(p) {
        p.connection.bind("click", function() {
            j.detach(this);
        });
    });

    j.addGroup({
        el:container1,
        id:"one",
        dropOverride:true,
        endpoint:[ [ "Dot", { radius:11 } ], [ "Dot", { radius:11 } ] ]
    });
    
    j.addToGroup("one", c1_1);
    j.addToGroup("one", c1_2);
    j.addToGroup("one", c1_3);
    var connectorPaintStyle = {
            strokeWidth: 2,
            stroke: "#32c24d",
            joinstyle: "round",
            outlineStroke: "white",
            outlineWidth: 2
        },
    // .. and this is the hover style.
        connectorHoverStyle = {
            strokeWidth: 2,
            stroke: "#32c24d",
            outlineWidth: 2,
            outlineStroke: "white"
        },
        endpointHoverStyle = {
            fill: "#32c24d",
            stroke: "#32c24d"
        },
        sourceEndpoint = {
                endpoint: "Dot",
                paintStyle: {
                    stroke: "#32c24d",
                    fill: "transparent",
                    radius: 2,
                    strokeWidth: 1
                },
                isSource: true,
                connector: [ "StateMachine", { stub: [40, 60], gap: 10, cornerRadius: 5, alwaysRespectStubs: true } ],
                connectorStyle: connectorPaintStyle,
                hoverPaintStyle: endpointHoverStyle,
                connectorHoverStyle: connectorHoverStyle,
                dragOptions: {},
                overlays: [
                    [ "Label", {
                        location: [0.5, 0.5],
                        label: "Drag",
                        cssClass: "endpointSourceLabel",
                        visible:false
                    } ]
                ]
            },
            targetEndpoint = {
                endpoint: "Dot",
                paintStyle: { fill: "#32c24d", radius: 7 },
                hoverPaintStyle: endpointHoverStyle,
                maxConnections: -1,
                dropOptions: { hoverClass: "hover", activeClass: "active" },
                isTarget: true,
                overlays: [
                    [ "Label", { location: [0.5, -0.5], label: "Drop", cssClass: "endpointTargetLabel", visible:false } ]
                ]
            };
    var sourceAnchors = [
        [ 0, 1, 0, 1 ],
        [ 0.25, 1, 0, 1 ],
        [ 0.5, 1, 0, 1 ],
        [ 0.75, 1, 0, 1 ],
        [ 1, 1, 0, 1 ]
    ];
    j.makeSource(c1_1, {
        filter:"a",
        filterExclude:true,
        maxConnections: 1,
        endpoint:[ "Dot", { radius: 1, cssClass:"small-blue" } ],
        anchor:sourceAnchors
    });
    j.makeTarget(container2, {
        dropOptions: { hoverClass: "hover" },
        anchor:"Top",
        endpoint:[ "Dot", { radius: 1, cssClass:"large-green" } ]
    });
    
    j.addGroup({
        el:container2,
        id:"two",
        dropOverride:true,
        endpoint:[ [ "Dot", { radius:11 } ], [ "Dot", { radius:11 } ] ]
    });  //(the default is to revert)
    j.addToGroup("two", c2_1);
    j.addToGroup("two", c2_2);
    j.addToGroup("two", c2_3);


    // the independent element that demonstrates the fact that it can be dropped onto a group
    j.draggable("standalone");
    // delete group button
    j.on(canvas, "click", ".del", function() {
        var g = this.parentNode.getAttribute("group");
        j.removeGroup(g, this.getAttribute("delete-all") != null);
    });

    jsPlumb.fire("jsPlumbDemoLoaded", j);

});