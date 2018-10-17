jsPlumb.ready(function () {

    var j = window.j = jsPlumb.getInstance({Endpoint: ["Dot", {radius: 2}],
        Connector:"StateMachine",
        HoverPaintStyle: {stroke: "#1e8151", strokeWidth: 2 },
        ConnectionOverlays: [
            [ "Arrow", {
                location: 1,
                id: "arrow",
                length: 10,
                foldback: 0.8
            } ]
        ],
        Container: "canvas"});

    j.bind("connection", function(p) {
        p.connection.bind("click", function() {
            j.detach(this);
        });
    });

    var evts = document.querySelector("#events");
    var _appendEvent = function(name, detail) {
        evts.innerHTML = "<br/><strong>" + name + "</strong><br/> " + detail + "<br/>" + evts.innerHTML;
    };
    j.bind("group:addMember", function(p) {
        _appendEvent("group:addMember", p.group.id + " - " + p.el.id);
    });
    j.bind("group:removeMember", function(p) {
        _appendEvent("group:removeMember", p.group.id + " - " + p.el.id);
    });
    j.bind("group:expand", function(p) {
        _appendEvent("group:expand", p.group.id);
    });
    j.bind("group:collapse", function(p) {
        _appendEvent("group:collapse", p.group.id);
    });
    j.bind("group:add", function(p) {
        _appendEvent("group:add", p.group.id);
    });
    j.bind("group:remove", function(p) {
        _appendEvent("group:remove", p.group.id);
    });
    // NOTE ordering here. we make one draggable before adding it to the group, and we add the other to the group
    //before making it draggable. they should both be constrained to the group extents.
    
    j.connect({source:c1_1, target:container2 ,  type:"basic"});
    
    j.addGroup({
        el:container1,
        id:"one",
        dropOverride:true,
        endpoint:[ [ "Dot", { radius:11 } ], [ "Dot", { radius:11 } ] ]
    });
    
    j.addToGroup("one", c1_1);
    j.addToGroup("one", c1_2);
    j.addToGroup("one", c1_3);

    j.draggable(c1_1);
    j.draggable(c1_2);
    j.draggable(c1_3);
    
    
    j.addGroup({
        el:container2,
        id:"two",
        dropOverride:true,
        endpoint:[ [ "Dot", { radius:11 } ], [ "Dot", { radius:11 } ] ]
    });  //(the default is to revert)
    j.addToGroup("two", c2_1);
    j.addToGroup("two", c2_2);
    j.addToGroup("two", c2_3);
    
    j.draggable(c2_1);
    j.draggable(c2_2);
    j.draggable(c2_3);

    // the independent element that demonstrates the fact that it can be dropped onto a group
    j.draggable("standalone");
    // delete group button
    j.on(canvas, "click", ".del", function() {
        var g = this.parentNode.getAttribute("group");
        j.removeGroup(g, this.getAttribute("delete-all") != null);
    });

    // collapse/expand group button
    j.on(canvas, "click", ".node-collapse", function() {
        var g = this.parentNode.getAttribute("group"), collapsed = j.hasClass(this.parentNode, "collapsed");

        j[collapsed ? "removeClass" : "addClass"](this.parentNode, "collapsed");
        j[collapsed ? "expandGroup" : "collapseGroup"](g);
    });

    jsPlumb.fire("jsPlumbDemoLoaded", j);

});