---
Author: Chris Ellis
Date: 2017-06-25
Code: bash
---
# Bergamot Monitoring API Reference

* [AlertsAPIRouter](#alertsapirouter)
* [HostAPIRouter](#hostapirouter)
* [LocationAPIRouter](#locationapirouter)
* [GroupAPIRouter](#groupapirouter)
* [ClusterAPIRouter](#clusterapirouter)
* [ServiceAPIRouter](#serviceapirouter)
* [TrapAPIRouter](#trapapirouter)
* [ResourceAPIRouter](#resourceapirouter)
* [TimePeriodAPIRouter](#timeperiodapirouter)
* [CommandAPIRouter](#commandapirouter)
* [ContactAPIRouter](#contactapirouter)
* [TeamAPIRouter](#teamapirouter)
* [CommentsAPIRouter](#commentsapirouter)
* [DowntimeAPIRouter](#downtimeapirouter)
* [ConfigAPIRouter](#configapirouter)
* [StatsAPIRouter](#statsapirouter)
* [UtilAPIRouter](#utilapirouter)
* [LamplighterAPIRouter](#lamplighterapirouter)

<p><a id="alertsapirouter"></a></p>
## AlertsAPIRouter

### GET `/api/alert/`

getAlerts

### GET `/api/alert/id/:id`

getAlert

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/alert/for-check/id/:id`

getAlertsForCheck

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/alert/current/for-check/id/:id`

getCurrentAlertForCheck

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### ANY `/api/alert/id/:id/acknowledge`

acknowledgeAlert

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

<p><a id="hostapirouter"></a></p>
## HostAPIRouter

### GET `/api/host/name/:name`

getHostByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/host/id/:id/execute`

executeHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/execute-services`

executeServicesOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/suppress-services`

suppressServicesOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/unsuppress-services`

unsuppressServicesOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/`

getHosts

### GET `/api/host/id/:id/suppress`

suppress

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/unsuppress`

unsuppress

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/name/:name/state`

getHostStateByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/host/id/:id/state`

getHostState

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/name/:name/services`

getHostServicesByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/host/id/:id/services`

getHostServices

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/name/:name/traps`

getHostTrapsByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/host/id/:id/traps`

getHostTraps

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/suppress-traps`

suppressTrapsOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/unsuppress-traps`

unsuppressTrapsOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/suppress-all`

suppressAllOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id/unsuppress-all`

unsuppressAllOnHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/host/id/:id`

getHost

#### Parameters

* `id` (type: UUID) (provided in the URL path)

<p><a id="locationapirouter"></a></p>
## LocationAPIRouter

### GET `/api/location/roots`

getRootLocations

### GET `/api/location/name/:name`

getLocationByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/location/id/:id/execute-all-hosts`

executeHostsInLocation

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/location/`

getLocations

### GET `/api/location/name/:name/children`

getLocationChildrenByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/location/name/:name/hosts`

getLocationHostsByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/location/id/:id/children`

getLocationChildren

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/location/id/:id/hosts`

getLocationHosts

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/location/id/:id`

getLocation

#### Parameters

* `id` (type: UUID) (provided in the URL path)

<p><a id="groupapirouter"></a></p>
## GroupAPIRouter

### GET `/api/group/roots`

getRootGroups

### GET `/api/group/name/:name`

getGroupByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/group/id/:id`

getGroup

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/group/id/:id/execute-all-checks`

executeChecksInGroup

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/group/`

getGroups

### GET `/api/group/id/:id/children`

getGroupChildren

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/group/id/:id/checks`

getGroupChecks

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/group/name/:name/children`

getGroupChildrenByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/group/name/:name/checks`

getGroupChecksByName

#### Parameters

* `name` (type: String) (provided in the URL path)

<p><a id="clusterapirouter"></a></p>
## ClusterAPIRouter

### GET `/api/cluster/name/:name`

getClusterByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/cluster/id/:id`

getCluster

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/id/:id/suppress`

suppressCluster

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/id/:id/unsuppress`

unsuppressCluster

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/`

getClusters

### GET `/api/cluster/name/:name/state`

getClusterStateByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/cluster/id/:id/state`

getClusterState

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/name/:name/resources`

getClusterResourcesByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/cluster/id/:id/resources`

getClusterResources

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/name/:name/references`

getClusterReferencesByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/cluster/id/:id/references`

getClusterReferences

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/id/:id/suppress-resources`

suppressResourcesOnCluster

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/cluster/id/:id/unsuppress-resources`

unsuppressResourcesOnCluster

#### Parameters

* `id` (type: UUID) (provided in the URL path)

<p><a id="serviceapirouter"></a></p>
## ServiceAPIRouter

### GET `/api/service/id/:id`

getService

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/service/id/:id/execute`

executeService

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/service/id/:id/suppress`

suppressService

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/service/id/:id/unsuppress`

unsuppressService

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/service/name/:host/:name`

getServiceByName

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

### GET `/api/service/id/:id/state`

getServiceState

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/service/name/:host/:name/state`

getServiceStateByName

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

<p><a id="trapapirouter"></a></p>
## TrapAPIRouter

### GET `/api/trap/id/:id`

getTrap

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/trap/id/:id/suppress`

suppressTrap

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/trap/id/:id/unsuppress`

unsuppressTrap

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/trap/name/:host/:name`

getTrapByName

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

### GET `/api/trap/id/:id/state`

getTrapState

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/trap/name/:host/:name/state`

getTrapStateByName

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

### ANY `/api/trap/id/:id/submit`

submitTrapStatus

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `status` (type: String) (provided as a query parameter)
* `output` (type: String) (provided as a query parameter)

<p><a id="resourceapirouter"></a></p>
## ResourceAPIRouter

### GET `/api/resource/id/:id/suppress`

suppressResource

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/resource/id/:id/unsuppress`

unsuppressResource

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/resource/name/:cluster/:name`

getResourceByName

#### Parameters

* `cluster` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

### GET `/api/resource/id/:id/state`

getResourceState

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/resource/name/:host/:name/state`

getResourceStateByName

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

### GET `/api/resource/id/:id`

getResource

#### Parameters

* `id` (type: UUID) (provided in the URL path)

<p><a id="timeperiodapirouter"></a></p>
## TimePeriodAPIRouter

### GET `/api/time-period/id/:id`

getTimePeriod

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/time-period/`

getTimePeriods

### GET `/api/time-period/name/:name`

getTimePeriodByName

#### Parameters

* `name` (type: String) (provided in the URL path)

<p><a id="commandapirouter"></a></p>
## CommandAPIRouter

### GET `/api/command/id/:id`

getCommand

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/command/`

getCommands

### GET `/api/command/name/:name`

getCommandByName

#### Parameters

* `name` (type: String) (provided in the URL path)

<p><a id="contactapirouter"></a></p>
## ContactAPIRouter

### GET `/api/contact/name-or-email/:nameOrEmail`

getContactByNameOrEmail

#### Parameters

* `nameOrEmail` (type: String) (provided in the URL path)

### GET `/api/contact/id/:id`

getContact

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/contact/name/:name`

getContactByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/contact/email/:email`

getContactByEmail

#### Parameters

* `email` (type: String) (provided in the URL path)

### GET `/api/contact/`

getContacts

<p><a id="teamapirouter"></a></p>
## TeamAPIRouter

### GET `/api/team/id/:id`

getTeam

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/team/`

getTeams

### GET `/api/team/name/:name`

getTeamByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/team/name/:name/children`

getTeamChildrenByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/team/name/:name/contacts`

getTeamContactsByName

#### Parameters

* `name` (type: String) (provided in the URL path)

### GET `/api/team/id/:id/children`

getTeamChildren

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/team/id/:id/contacts`

getTeamContacts

#### Parameters

* `id` (type: UUID) (provided in the URL path)

<p><a id="commentsapirouter"></a></p>
## CommentsAPIRouter

### GET `/api/comment/id/:id`

getComment

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/comment/id/:id/remove`

removeComment

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/comment/for-object/id/:id`

getCommentsForObject

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `offset` (type: Long) (provided as a query parameter)
* `limit` (type: Long) (provided as a query parameter)

### ANY `/api/comment/add-comment-to-check/id/:id`

addCommentToCheck

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

### ANY `/api/comment/add-comment-to-alert/id/:id`

addCommentToAlert

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

### ANY `/api/comment/add-comment-to-downtime/id/:id`

addCommentToDowntime

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

### ANY `/api/comment/add-comment-to-object/id/:id`

addCommentToObject

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

<p><a id="downtimeapirouter"></a></p>
## DowntimeAPIRouter

### GET `/api/downtime/id/:id`

getDowntime

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/downtime/id/:id/remove`

removeDowntime

#### Parameters

* `id` (type: UUID) (provided in the URL path)

### GET `/api/downtime/for-object/id/:id`

getDowntimeForObject

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `past` (type: Integer) (provided as a query parameter)
* `future` (type: Integer) (provided as a query parameter)

### ANY `/api/downtime/add-downtime-to-check/id/:id`

addDowntimeToCheck

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `starts` (type: Date) (provided as a query parameter)
* `ends` (type: Date) (provided as a query parameter)
* `summary` (type: String) (provided as a query parameter)
* `description` (type: String) (provided as a query parameter)

<p><a id="configapirouter"></a></p>
## ConfigAPIRouter

### ANY `/api/config/exists/:type/:name`

objectExists

#### Parameters

* `type` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

### ANY `/api/config/icon/`

listIcons

<p><a id="statsapirouter"></a></p>
## StatsAPIRouter

### ANY `/api/stats/transitions/check/id/:id`

getCheckTransitions

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `offset` (type: Long) (provided as a query parameter)
* `limit` (type: Long) (provided as a query parameter)

<p><a id="utilapirouter"></a></p>
## UtilAPIRouter

### GET `/api/util/version/number`

versionNumber

### GET `/api/util/version/codename`

versionCodeName

### GET `/api/util/id/new`

newId

### GET `/api/util/id/new/:count`

newIds

#### Parameters

* `count` (type: Integer) (provided in the URL path)

### GET `/api/util/version`

version

<p><a id="lamplighterapirouter"></a></p>
## LamplighterAPIRouter

### ANY `/api/lamplighter/check/id/:id/readings`

getReadingsByCheck

#### Parameters

* `id` (type: UUID) (provided in the URL path)

