var FlexIFrame = {
    get: function(id)
    {
        var iframe = document.getElementById(id);
        if(!iframe)
            return FlexIFrame.create(id);
        return iframe;
    },
 
    create: function(id)
    {
        var iframe = document.createElement('iframe');
        iframe.id = id;
        iframe.frameborder = 0;
        iframe.style.position = "absolute";
        iframe.style.zIndex = 1;
        iframe.style.border = "none";
        document.body.insertBefore(iframe, document.body.firstChild);
        return iframe;
    },
 
    resize: function(id, width, height)
    {
        var iframe = FlexIFrame.get(id);
        iframe.style.width = width + "px";
        iframe.style.height = height + "px";
    },
 
    move: function(id, x, y)
    {
        var iframe = FlexIFrame.get(id);
        iframe.style.left = x + "px";
        iframe.style.top = y + "px";
    },
 
    navigate: function(id, url)
    {
        var iframe = FlexIFrame.get(id);
        iframe.src = url;
    },
 
    visibility: function(id, visible)
    {
        var iframe = FlexIFrame.get(id);
        iframe.style.display = visible ? "block" : "none";
    }
}