#This file create the initial database
from flask_restx import Api
from .auth import api as auth
from .jobseeker import api as jobseeker
from .employer import api as employer
from .agent import api as agent

api = Api(
    title = 'zp API',
    version = '0.0.2',
    description = 'An API backend'
)

api.add_namespace(auth)
api.add_namespace(jobseeker)
api.add_namespace(employer)
api.add_namespace(agent)
