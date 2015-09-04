# Bergamot Permissions

## UI Specific

* ui.access                 - allow access to the UI
* ui.view.stats             - allow access to see stats about checks
* ui.view.stats.transitions - allow access to see transitions of checks
* ui.view.readings          - allow access to see readings (graphs) on checks
* ui.sign.agent             - allow the signing of Agent certificates
* ui.generate.agent         - allow the generation of Agent key pairs
* ui.admin                  - allow access to the admin ui

## API Specific

* api.access          - allow access to the API
* api.sign.agent      - allow the signing of Agent certificates via the API

## Object Permissions

* read             - read the state / information of an object
* read.config      - read the objects configuration snippet
* read.comment     - read the comments on an object
* read.downtime    - read the downtimes on an object
* read.readings    - read the readings (graphs) of an object
* enable           - enable a check
* disable          - disable a check
* execute          - execute a check
* suppress         - suppress a check
* unsuppress       - unsuppress a check
* submit           - submit a passive result
* acknowledge      - acknowledge an alert of a check
* write            - modify an object
* write.comment    - add a comment to an object
* write.downtime   - add a downtime to an object
* create           - create an object
* remove           - remove an object
* remove.comment   - remove a comment from an object
* remove.downtime  - remove a downtime from an object

## Other Permissions

* sign.agent          - allow the signing of Agent certificates
* config.export       - allow the export of the system configuration
* config.change.apply - allow the application of configuration changes
