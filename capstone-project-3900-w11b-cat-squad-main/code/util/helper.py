from flask_restx import abort

def unpack(j, *args, **kargs):
    r = [j.get(arg, None) for arg in args]
    if kargs.get("required", True):
        [abort(kargs.get("Missing Arguments", 400)) for e in r if e == None]
    return r